package com.gim.agora.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
