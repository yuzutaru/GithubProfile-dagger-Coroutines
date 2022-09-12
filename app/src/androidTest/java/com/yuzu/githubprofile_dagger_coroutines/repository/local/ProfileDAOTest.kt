package com.yuzu.githubprofile_dagger_coroutines.repository.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Yustar Pramudana on 12/09/2022.
 */

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileDAOTest {
    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)

    private val profileDB = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext, ProfileDB::class.java)
        .allowMainThreadQueries()
        .setTransactionExecutor(dispatcher.asExecutor())
        .setQueryExecutor(dispatcher.asExecutor())/*.build()*/

    // execute before every test case
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    // execute after every test case
    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    //getAllProfilesBySingle
    /* @Test
     fun getAllProfileSingleInputTest() = testScope.runTest {
         profileDB.profileDAO().insert(Profile(0))
         val profileDataList = profileDB.profileDAO().getAllProfiles()
         Assert.assertEquals(profileDataList.size, 1)
     }

     @Test
     fun getAllProfileSingleInputOneByOneTest() {
         runBlocking {
             profileDB.profileDAO().insert(Profile(0))
             db.profileDAO().insert(Profile(1))
             val profileDataList = db.profileDAO().getAllProfiles()
             Assert.assertEquals(profileDataList.size, 2)
         }
     }

     @Test
     fun getAllProfileSingleInputOnConflictTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0))
             db.profileDAO().insert(Profile(0))
             val profileDataList = db.profileDAO().getAllProfiles()
             Assert.assertEquals(profileDataList.size, 1)
         }
     }

     @Test
     fun getAllProfileListInputTest() {
         runBlocking {
             db.profileDAO().insert(listOf(Profile(0), Profile(1)))
             val profileDataList = db.profileDAO().getAllProfiles()
             Assert.assertEquals(profileDataList.size, 2)
         }
     }

     //GetProfileByLogin
     @Test
     fun getProfileByLoginSingleInputTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0, "Yuzutaru"))
             val profileData = db.profileDAO().getProfile("Yuzutaru")
             Assert.assertNotNull(profileData)
         }
     }

     @Test
     fun getProfileByLoginSingleInputOneByOneTest() {
        runBlocking {
            db.profileDAO().insert(Profile(0, "Yuzutaru"))
            db.profileDAO().insert(Profile(1, "Yustar"))
            val profileData = db.profileDAO().getProfile("Yuzutaru")
            Assert.assertNotNull(profileData)
        }
     }

     @Test
     fun getProfileByLoginSingleInputOnConflictTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0, "Yuzutaru"))
             db.profileDAO().insert(Profile(0, "Yustar"))
             val profileData = db.profileDAO().getProfile("Yustar")
             Assert.assertNotNull(profileData)
         }
     }

     @Test
     fun getProfileByLoginListInputTest() {
         runBlocking {
             db.profileDAO().insert(listOf(Profile(0, "Yuzutaru"), Profile(1, "Yustar")))
             val profileData = db.profileDAO().getProfile("Yuzutaru")
             Assert.assertNotNull(profileData)
         }
     }

     @Test
     fun insertProfileDataTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0, "yuzutaru"))
             val profileDataList = db.profileDAO().getAllProfiles().size
             Assert.assertEquals(profileDataList, 1)
         }
     }

     @Test
     fun insertProfileDataOneByOneTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0, "yuzutaru"))
             db.profileDAO().insert(Profile(1, "yustar"))
             val profileDataList = db.profileDAO().getAllProfiles().size
             Assert.assertEquals(profileDataList, 2)
         }
     }

     @Test
     fun insertProfileDataOnConflictTest() {
         runBlocking {
             db.profileDAO().insert(Profile(0, "yuzutaru"))
             db.profileDAO().insert(Profile(0, "yustar"))
             val profileDataList = db.profileDAO().getAllProfiles().size
             Assert.assertEquals(profileDataList, 1)
         }
     }

     @Test
     fun insertProfileDataListInputTest() {
         runBlocking {
             db.profileDAO().insert(listOf(Profile(0, "yuzutaru"), Profile(1, "yustar")))
             val profileDataList = db.profileDAO().getAllProfiles().size
             Assert.assertEquals(profileDataList, 2)
         }
     }*/
}