apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.agendaDomain))

    "implementation"(Coil.coilCompose)
    "implementation"("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
}
