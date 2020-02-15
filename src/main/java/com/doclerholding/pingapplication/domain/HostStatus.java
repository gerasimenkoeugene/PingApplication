package com.doclerholding.pingapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class HostStatus {

    @JsonProperty("host")
    private String host;

    @JsonProperty("icmp_ping")
    private String icmpPing;

    @JsonProperty("tcp_ping")
    private String tcpPing;

    @JsonProperty("trace")
    private String trace;

    public String getHost() {
        return this.host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getIcmpPing() {
        return this.icmpPing;
    }

    public void setIcmpPing(final String icmpPing) {
        this.icmpPing = icmpPing;
    }

    public String getTcpPing() {
        return this.tcpPing;
    }

    public void setTcpPing(final String tcpPing) {
        this.tcpPing = tcpPing;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setTrace(final String trace) {
        this.trace = trace;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HostStatus.class.getSimpleName() + "[", "]")
            .add("host='" + host + "'")
            .add("icmpPing='" + icmpPing + "'")
            .add("tcpPing='" + tcpPing + "'")
            .add("trace='" + trace + "'")
            .toString();
    }
}
