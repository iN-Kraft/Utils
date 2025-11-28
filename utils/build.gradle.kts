plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.publish)
    `maven-publish`
}

val libGroup = "dev.datlag.inkraft"
val libName = "utils"

group = libGroup
version = libVersion

kotlin {
    androidLibrary {
        compileSdk = 36
        minSdk = 21

        namespace = "$libGroup.$libName"
    }
    jvm()

    val androidNativeTargets = listOf(
        androidNativeX86 {
            binaries {
                sharedLib()
                staticLib()
            }
        },
        androidNativeX64 {
            binaries {
                sharedLib {
                    linkerOpts += listOf(
                        "-Wl,-z,max-page-size=16384",
                        "-Wl,-z,common-page-size=16384",
                        "-v"
                    )
                }
                staticLib {
                    linkerOpts += listOf(
                        "-Wl,-z,max-page-size=16384",
                        "-Wl,-z,common-page-size=16384",
                        "-v"
                    )
                }
            }
        },
        androidNativeArm32 {
            binaries {
                sharedLib()
                staticLib()
            }
        },
        androidNativeArm64 {
            binaries {
                sharedLib()
                staticLib()
            }
        }
    )

    linuxX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    linuxArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    mingwX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    macosX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    macosArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    iosX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    iosArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    iosSimulatorArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    tvosX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    tvosArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    tvosSimulatorArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    watchosX64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    watchosArm32 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    watchosArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    watchosSimulatorArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }
    watchosDeviceArm64 {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    js {
        browser()
        nodejs()
    }

    wasmJs {
        browser()
        nodejs()
    }

    wasmWasi {
        nodejs()
    }

    applyDefaultHierarchyTemplate()

    androidNativeTargets.forEach { target ->
        target.compilations.getByName("main") {
            cinterops {
                create("sysprop") {
                    definitionFile.set(project.layout.projectDirectory.file("src/androidNativeMain/cinterop/sysprop.def"))
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines)
            implementation(libs.reflection)
        }

        androidMain.dependencies {
            implementation(libs.android.annotations)
            implementation(libs.android.core)
        }

        val javaMain by creating {
            dependsOn(commonMain.get())

            androidMain.orNull?.dependsOn(this)
            jvmMain.orNull?.dependsOn(this)
        }

        val weakRefMain by creating {
            dependsOn(commonMain.get())

            javaMain.dependsOn(this)
            nativeMain.orNull?.dependsOn(this)
            jsMain.orNull?.dependsOn(this)
        }

        val systemMain by creating {
            dependsOn(commonMain.get())

            javaMain.dependsOn(this)
            androidNativeMain.orNull?.dependsOn(this)
        }
    }
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/iN-Kraft/Utils")
            credentials(PasswordCredentials::class)
        }

        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("release"))
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = libGroup,
        artifactId = libName,
        version = libVersion
    )

    pom {
        name.set("Utils")
        description.set("Utility for every Kotlin Multiplatform project.")
        inceptionYear.set("2025")
        url.set("https://github.com/iN-Kraft/Utils")

        licenses {
            license {
                name.set("GNU Lesser General Public License version 3")
                url.set("https://opensource.org/license/lgpl-3-0")
            }
        }
        developers {
            developer {
                id.set("DatL4g")
                name.set("Jeff Retz")
                url.set("https://github.com/DatL4g")
            }
        }
        scm {
            url.set("https://github.com/iN-Kraft/Utils")
            connection.set("scm:git:git://github.com/iN-Kraft/Utils.git")
            developerConnection.set("scm:git:ssh://git@github.com/iN-Kraft/Utils.git")
        }
    }
}