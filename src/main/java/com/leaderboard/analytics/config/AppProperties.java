package com.leaderboard.analytics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name = "Analytics Service";
    private String version = "1.0.0";
    private boolean debugMode = false;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public boolean isDebugMode() { return debugMode; }
    public void setDebugMode(boolean debugMode) { this.debugMode = debugMode; }
    
    @Override
    public String toString() {
        return "AppProperties [name=" + name + ", version=" + version + ", debugMode=" + debugMode + "]";
    }
}
