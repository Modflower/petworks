loom {
	accessWidenerPath = file("dev.accesswidener")
}

fabricApi {
	configureDataGeneration {
		createSourceSet = true
		strictValidation = true
		// TODO: Whenever Loom fixes the bug, revisit this.
		addToResources = true
		modId = "petworks"
		client = true
	}
}

dependencies {
	compileOnly(libs.mixin)

	// Since this is Xplat, but we want data generation, I'm enforcing that the classpath
	// pollution remains only within the datagen implementation, minimising the risk of
	// depending on Fabric API.

	// Now I'm not sure how flexible this will be, but we'll figure it out.
	//val fapi = libs.versions.fabric.api.get()
	modRuntimeOnly(libs.fabric.loader)
	"modDatagenImplementation"(libs.fabric.loader)
	"modDatagenImplementation"(libs.fabric.api)
	//"modDatagenImplementation"(fabricApi.module("fabric-data-generation-api-v1", fapi))

	modCompileOnly(variantOf(rootProject.libs.emi) { classifier("api") })
}

tasks {
	sourcesJar {
		dependsOn("runDatagen")
	}
}
