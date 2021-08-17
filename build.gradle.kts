plugins {
    java
}

version = "1.5.21"

repositories {
    mavenCentral()
}

val shadowing by configurations.creating

dependencies {
    shadowing("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version")
    shadowing("org.jetbrains.kotlin:kotlin-reflect:$version")
}

tasks.jar {
    for (kotlinJar in shadowing) {
        from(zipTree(kotlinJar)) {
            exclude("META-INF/versions/9/module-info.class")
        }
    }
    archiveBaseName.set("aaaa-preload-newer-kotlin")
    manifest.attributes("FMLCorePlugin" to "invalid")
}
