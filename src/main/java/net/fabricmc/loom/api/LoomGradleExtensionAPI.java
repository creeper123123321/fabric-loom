/*
 * This file is part of fabric-loom, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2021 FabricMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.fabricmc.loom.api;

import java.io.File;
import java.util.List;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.publish.maven.MavenPublication;
import org.jetbrains.annotations.ApiStatus;

import net.fabricmc.loom.api.decompilers.LoomDecompiler;
import net.fabricmc.loom.api.mappings.layered.spec.LayeredMappingSpecBuilder;
import net.fabricmc.loom.configuration.ide.RunConfigSettings;
import net.fabricmc.loom.configuration.processors.JarProcessor;
import net.fabricmc.loom.util.DeprecationHelper;

/**
 * This is the public api available exposed to build scripts.
 */
public interface LoomGradleExtensionAPI {
	@ApiStatus.Internal
	DeprecationHelper getDeprecationHelper();

	RegularFileProperty getAccessWidenerPath();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default File getAccessWidener() {
		getDeprecationHelper().replaceWithInLoom0_11("accessWidener", "accessWidenerPath");
		return getAccessWidenerPath().getAsFile().getOrNull();
	}

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default void setAccessWidener(File file) {
		getDeprecationHelper().replaceWithInLoom0_11("accessWidener", "accessWidenerPath");
		getAccessWidenerPath().set(file);
	}

	Property<Boolean> getShareRemapCaches();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default void setShareCaches(boolean shareCaches) {
		getDeprecationHelper().replaceWithInLoom0_11("shareCaches", "shareRemapCaches");
		getShareRemapCaches().set(shareCaches);
	}

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default boolean isShareCaches() {
		getDeprecationHelper().replaceWithInLoom0_11("shareCaches", "shareRemapCaches");
		return getShareRemapCaches().get();
	}

	default void shareCaches() {
		getShareRemapCaches().set(true);
	}

	ListProperty<LoomDecompiler> getGameDecompilers();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default List<LoomDecompiler> getDecompilers() {
		getDeprecationHelper().replaceWithInLoom0_11("decompilers", "gameDecompilers");
		return getGameDecompilers().get();
	}

	default void addDecompiler(LoomDecompiler decompiler) {
		getGameDecompilers().add(decompiler);
	}

	ListProperty<JarProcessor> getGameJarProcessors();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default List<JarProcessor> getJarProcessors() {
		getDeprecationHelper().replaceWithInLoom0_11("jarProcessors", "gameJarProcessors");
		return getGameJarProcessors().get();
	}

	default void addJarProcessor(JarProcessor processor) {
		getGameJarProcessors().add(processor);
	}

	ConfigurableFileCollection getLog4jConfigs();

	default Dependency officialMojangMappings() {
		return layered(LayeredMappingSpecBuilder::officialMojangMappings);
	}

	Dependency layered(Action<LayeredMappingSpecBuilder> action);

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default String getRefmapName() {
		getDeprecationHelper().replaceWithInLoom0_11("refmapName", "mixin.defaultRefmapName");
		return getMixin().getDefaultRefmapName().get();
	}

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default void setRefmapName(String refmapName) {
		getDeprecationHelper().replaceWithInLoom0_11("refmapName", "mixin.defaultRefmapName");
		getMixin().getDefaultRefmapName().set(refmapName);
	}

	Property<Boolean> getRemapArchives();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default boolean isRemapMod() {
		getDeprecationHelper().replaceWithInLoom0_11("remapMod", "remapArchives");
		return getRemapArchives().get();
	}

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default void setRemapMod(boolean remapMod) {
		getDeprecationHelper().replaceWithInLoom0_11("remapMod", "remapArchives");
		getRemapArchives().set(remapMod);
	}

	void runs(Action<NamedDomainObjectContainer<RunConfigSettings>> action);

	NamedDomainObjectContainer<RunConfigSettings> getRunConfigs();

	void mixin(Action<MixinExtensionAPI> action);

	@ApiStatus.Experimental
	// TODO: move this from LoomGradleExtensionAPI to LoomGradleExtension once getRefmapName & setRefmapName is removed.
	MixinExtensionAPI getMixin();

	Property<String> getCustomMinecraftManifest();

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default void setCustomManifest(String customManifest) {
		getDeprecationHelper().replaceWithInLoom0_11("customManifest", "customMinecraftManifest");
		getCustomMinecraftManifest().set(customManifest);
	}

	@Deprecated(forRemoval = true)
	@ApiStatus.ScheduledForRemoval(inVersion = "0.11")
	default String getCustomManifest() {
		getDeprecationHelper().replaceWithInLoom0_11("customManifest", "customMinecraftManifest");
		return getCustomMinecraftManifest().getOrNull();
	}

	/**
	 * If true, Loom will replace the {@code -dev} jars in the {@code *Elements} configurations
	 * with remapped outgoing variants.
	 *
	 * <p>Will only apply if {@link #getRemapArchives()} is also true.
	 *
	 * @return the property controlling the setup of remapped variants
	 */
	Property<Boolean> getSetupRemappedVariants();

	/**
	 * Disables the deprecated POM generation for a publication.
	 * This is useful if you want to suppress deprecation warnings when you're not using software components.
	 *
	 * <p>Experimental API: Will be removed in Loom 0.12 together with the deprecated POM generation functionality.
	 *
	 * @param publication the maven publication
	 */
	@ApiStatus.Experimental
	void disableDeprecatedPomGeneration(MavenPublication publication);

	/**
	 * Reads the mod version from the fabric.mod.json file located in the main sourcesets resources.
	 * This is useful if you want to set the gradle version based of the version in the fabric.mod.json file.
	 *
	 * @return the version defined in the fabric.mod.json
	 */
	String getModVersion();

	/**
	 * When true loom will apply transitive access wideners from compile dependencies.
	 *
	 * @return the property controlling the transitive access wideners
	 */
	Property<Boolean> getEnableTransitiveAccessWideners();
}
