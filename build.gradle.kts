plugins {
    base
}

version = "2.1.20"

repositories {
    mavenCentral()
}

val shadowing by configurations.creating

dependencies {
    shadowing("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version")
    shadowing("org.jetbrains.kotlin:kotlin-reflect:$version")
}

val jar by tasks.creating(Jar::class) {
    for (kotlinJar in shadowing) {
        from(zipTree(kotlinJar)) {
            exclude("META-INF/versions/9/module-info.class")
        }
    }
    archiveBaseName.set("aaaa-preload-newer-kotlin")
    manifest.attributes("FMLCorePlugin" to "invalid")
}
tasks.assemble {
    dependsOn(jar)
}
