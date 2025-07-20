package com.leaderboard.analytics.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.health.v1.HealthCheckRequest;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.health.v1.HealthGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HealthCheckGrpcService extends HealthGrpc.HealthImplBase {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckGrpcService.class);

    @Override
    public void check(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver){

        logger.debug("gRPC health check requested for service: {}", request.getService());

        HealthCheckResponse response = HealthCheckResponse.newBuilder()
            .setStatus(HealthCheckResponse.ServingStatus.SERVING)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void watch(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responsObserver){

        HealthCheckResponse response = HealthCheckResponse.newBuilder()
            .setStatus(HealthCheckResponse.ServingStatus.SERVING)
            .build();

        responsObserver.onNext(response);
        responsObserver.onCompleted();
    }
}
