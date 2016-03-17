package buildcraft.api.transport.pluggable;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.vertex.VertexFormat;

public interface IPluggableModelBaker<K extends PluggableModelKey<K>> {
    /** @return The vertex format used to generate the quads. */
    VertexFormat getVertexFormat();

    ImmutableList<BakedQuad> bake(K key);
}
