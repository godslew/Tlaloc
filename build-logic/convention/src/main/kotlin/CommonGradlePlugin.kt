import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class MainGradlePlugin: Plugin<Project> {

  val Project.libs
    get(): VersionCatalogsExtension = extensions.getByType<VersionCatalogsExtension>()

  override fun apply(project: Project) {
  }

  private fun applyPlugins(project: Project) {
    project.apply {
      plugin("android-library")
      plugin("kotlin-android")
      plugin("kotlin-kapt")
      plugin("dagger.hilt.android.plugin")
    }
  }

  private fun setProjectConfig(project: Project) {
    project.android().apply {
      compileSdk =

      defaultConfig {
        minSdk = project.libs.libraryAliases.
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      }

      compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
      }
    }
  }

  private fun Project.android(): LibraryExtension {
    return extensions.getByType(LibraryExtension::class.java)
  }

}
