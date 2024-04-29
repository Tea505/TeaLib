## Getting Started
To get started with TeaLib for your robotics projects, simply install the library and explore the clear documentation provided. 
With its user-friendly interface and robust functionality, TeaLib makes robotics programming easier, 
freeing up teams to focus on innovation and problem-solving.

## Installation 
To install the library, in `settings.gradle` add:
</br>

   ```gradle
   dependencyResolutionManagement {
		 repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		    repositories {
			mavenCentral()
                   maven { url "https://jitpack.io" }
		}
	}
   ```  

Then in `build.dependencies.gradle` add:
</br>

   ```gradle
   repositories { 
        jcenter()
        mavenCentral()
        maven { url "https://jitpack.io" }
   }
   ```  

Afterwards in your team code module's `build.gradle` add: 
</br>

   ```gradle
    dependencies {
        implementation 'com.github.Tea505:TeaLib:Tag'
    }
   ```

Replacing `TAG` with the latest release or tag.
</br>
</br>
[![](https://jitpack.io/v/Tea505/TeaLib.svg)](https://jitpack.io/#Tea505/TeaLib)

## Documentation 💭✍


