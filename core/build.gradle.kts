apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)
}
