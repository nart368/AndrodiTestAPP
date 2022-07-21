package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class ChannelsSetting {
    private String channel;
    private Settings settings;

    public ChannelsSetting(){}

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
