package com.leaderboard.analytics.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leaderboard.analytics.service.AnalyticsService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AnalyticsGrpcService extends AnalyticsServiceGrpc.AnalyticsServiceImplBase {

    public static final Logger logger = LoggerFactory.getLogger(AnalyticsGrpcService.class);

    private final AnalyticsService analyticsService;

    public AnalyticsGrpcService(AnalyticsService analyticsService){
        this.analyticsService = analyticsService;
    }

    @Override
    public void triggerMatchAnalysis(TriggerRequest request, StreamObserver<TriggerResponse> responseObserver){

        int matchId = request.getMatchId();
        logger.info("Received gRPC request for match analysis: matchId={}", matchId);

        try{
            //Triggering the async analysis for now, will add DB operations later
            analyticsService.processAnalysisAsync(matchId)
                .thenAccept(result -> {
                    logger.info("Analysis completed for matchId={}, {}", matchId, result);
                })
                .exceptionally(thrown -> {
                    logger.error("Analysis failed for match={}", matchId, thrown);
                    return null;
                });

            TriggerResponse response = TriggerResponse.newBuilder()
                .setStatus("Analysis triggered successfully for match " + matchId)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            logger.info("gRPC response sent for matchId={}", matchId);
        } 
        catch (Exception ex){
            logger.info("Error processing gRPC request for matchId={}", matchId, ex);

            responseObserver.onError(io.grpc.Status.INTERNAL
                .withDescription("Internal Server Error " + ex.getMessage())
                .asRuntimeException());
        }
    }
}
