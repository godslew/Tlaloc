val ktlint by configurations.creating

dependencies {
  ktlint("com.pinterest.ktlint:ktlint-cli:1.2.1")
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

@Suppress("UNUSED_VARIABLE")
val ktlintCheck by tasks.creating(JavaExec::class) {
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  group = "ktlint"
  description = "Check Kotlin code style."
  classpath = ktlint
  mainClass.set("com.pinterest.ktlint.Main")
  args = listOf("src/**/*.kt")
}

@Suppress("UNUSED_VARIABLE")
val ktlintFormat by tasks.creating(JavaExec::class) {
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  group = "ktlint"
  description = "Fix Kotlin code style deviations."
  classpath = ktlint
  mainClass.set("com.pinterest.ktlint.Main")
  args = listOf("-F", "src/**/*.kt")
}
