plugins {
	java
	id("gay.ampflower.BuildPlugin")
	alias(libs.plugins.loom)
	alias(libs.plugins.minotaur)
}

val github: String by project
val modrinthId: String by project

val excluded = setOf(rootProject, project(":xplat"))

allprojects {
	apply(plugin = "java")
	apply(plugin = "gay.ampflower.BuildPlugin")
	apply(plugin = rootProject.libs.plugins.loom.get().pluginId)
	apply(plugin = rootProject.libs.plugins.minotaur.get().pluginId)

	version = meta.globalVersion

	base {
		if (project != rootProject) {
			archivesName.set(rootProject.name + '-' + project.name)
		}
	}

	loom {
		mixin {
			useLegacyMixinAp = true
			defaultRefmapName = "petworks.refmap.json"
		}

		runs {
			named("client") {
				client()
				configName = "${project.name.replaceFirstChar(Char::uppercase)} Client"
				ideConfigGenerated(project !in excluded)
				runDir("run")
			}
			named("server") {
				server()
				configName = "${project.name.replaceFirstChar(Char::uppercase)} Server"
				ideConfigGenerated(project !in excluded)
				runDir("run")
			}
		}
	}

	mod {
		id.set("petworks")
	}

	java {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
		withSourcesJar()
		withJavadocJar()
	}

	repositories {
		mavenLocal()
		mavenCentral()

		maven("https://maven.neoforged.net/releases") { name = "Neoforged" }
		maven("https://files.minecraftforge.net/maven/") { name = "Forge" }
		maven("https://maven.quiltmc.org/repository/release") { name = "Quilt" }
		maven("https://api.modrinth.com/maven") { name = "Modrinth" }
		maven("https://maven.terraformersmc.com") { name = "TerraformersMC" }
		maven("https://maven.ladysnake.org/releases") { name = "Ladysnake Libs" }
		maven("https://maven.theillusivec4.top/") { name = "TheIllusiveC4" }
	}

	dependencies {
		minecraft(rootProject.libs.minecraft)
		mappings(variantOf(rootProject.libs.yarn) { classifier("v2") })
	}

	tasks {
		withType<JavaCompile> {
			options.release.set(21)
			options.encoding = "UTF-8"
			options.isDeprecation = true
			options.isWarnings = true
		}
		withType<Jar> {
			from("LICENSE*") {
				rename { "${it}_${rootProject.name}" }
			}
		}

		processResources {
			if (project !in excluded) {
				dependsOn(project(":xplat").tasks.named("runDatagen"))
			}
			val map =
				mapOf(
					"id" to project.name,
					"java" to java.targetCompatibility.majorVersion,
					"version" to project.version,
					"sources" to github,
					"issues" to "$github/issues",
					"description" to project.description,
					"projectVersion" to meta.projectVersion,
					"modrinthId" to modrinthId,
					"forgeRequired" to libs.versions.forge.loader.get().let {
						val s = it.indexOf('-') + 1
						it.substring(s, it.indexOf('.', s))
					},
					"minecraftVersion" to libs.versions.minecraft.version.get(),
					"minecraftRequired" to libs.versions.minecraft.required.get()
				)
			inputs.properties(map)

			filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "META-INF/mods.toml")) {
				expand(map)
			}
			exclude("*/.editorconfig")
		}
		javadoc {
			(options as StandardJavadocDocletOptions).tags("reason:a:Reason")
		}
		register("publish")
	}
}
