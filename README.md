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
</br? 

   ```  
    dependencies {
        implementation 'com.github.Tea505:TeaLib:Tag'
    }
    
   ```  

Replacing `TAG` with the latest release or tag.
[![](https://jitpack.io/v/Tea505/TeaLib.svg)](https://jitpack.io/#Tea505/TeaLib)
