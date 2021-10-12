package com.users.management.infrastructure.configuration.environment;

public class EnvironmentParameters {

    private final long expirationTime;
    private final String headerString;
    private final String tokenPrefix;
    private final String secret;

    public EnvironmentParameters(final String expirationTime,
        final String headerString,
        final String tokenPrefix,
        final String secret) {

        this.expirationTime = Long.parseLong(expirationTime);
        this.headerString = headerString;
        this.tokenPrefix = tokenPrefix;
        this.secret = secret;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }
    public String getHeaderString() {
        return this.headerString;
    }
    public String getTokenPrefix() {
        return this.tokenPrefix;
    }
    public String getSecret() {
        return this.secret;
    }
}
