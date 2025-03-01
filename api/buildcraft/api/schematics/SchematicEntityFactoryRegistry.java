package buildcraft.api.schematics;

import buildcraft.api.core.BuildCraftAPI;
import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SchematicEntityFactoryRegistry {
    private static final Set<SchematicEntityFactory<?>> FACTORIES = new TreeSet<>();

    public static <S extends ISchematicEntity> void registerFactory(String name,
                                                                    int priority,
                                                                    Predicate<SchematicEntityContext> predicate,
                                                                    Supplier<S> supplier) {
        FACTORIES.add(new SchematicEntityFactory<>(
                BuildCraftAPI.nameToResourceLocation(name),
                priority,
                predicate,
                supplier
        ));
    }

    // Calen no usage in 1.12.2
    // EntityList.class not present in 1.18.2
    public static <S extends ISchematicEntity> void registerFactory(
            String name,
            int priority,
            List<ResourceLocation> entities,
            Supplier<S> supplier
    ) {
        registerFactory(
                name,
                priority,
//            context -> entities.contains(EntityList.getKey(context.entity)),
                context -> entities.contains(context.entity.getType().builtInRegistryHolder().key().location()),
                supplier
        );
    }

    public static List<SchematicEntityFactory<?>> getFactories() {
        return ImmutableList.copyOf(FACTORIES);
    }

    @Nonnull
    public static <S extends ISchematicEntity> SchematicEntityFactory<S> getFactoryByInstance(S instance) {
        // noinspection unchecked
        return (SchematicEntityFactory<S>) FACTORIES.stream()
                .filter(schematicEntityFactory -> schematicEntityFactory.clazz == instance.getClass())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Didn't find a factory for " + instance.getClass()));
    }

    @Nullable
    public static SchematicEntityFactory<?> getFactoryByName(ResourceLocation name) {
        return FACTORIES.stream()
                .filter(schematicEntityFactory -> schematicEntityFactory.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
