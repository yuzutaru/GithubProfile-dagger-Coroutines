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
        .setQueryExecutor(dispatcher.asExecutor()).build()

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
    @Test
    fun getAllProfileSingleInputTest() = testScope.runTest {
         profileDB.profileDAO().insert(Profile(0))
         val profileDataList = profileDB.profileDAO().getAllProfiles()
         Assert.assertEquals(profileDataList.size, 1)
    }

    @Test
    fun getAllProfileSingleInputOneByOneTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0))
        profileDB.profileDAO().insert(Profile(1))
        val profileDataList = profileDB.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 2)
    }

    @Test
    fun getAllProfileSingleInputOnConflictTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0))
        profileDB.profileDAO().insert(Profile(0))
        val profileDataList = profileDB.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 1)
    }

    @Test
    fun getAllProfileListInputTest() = testScope.runTest {
        profileDB.profileDAO().insert(listOf(Profile(0), Profile(1)))
        val profileDataList = profileDB.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 2)
    }

    //GetProfileByLogin
    @Test
    fun getProfileByLoginSingleInputTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "Yuzutaru"))
        val profileData = profileDB.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginSingleInputOneByOneTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "Yuzutaru"))
        profileDB.profileDAO().insert(Profile(1, "Yustar"))
        val profileData = profileDB.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginSingleInputOnConflictTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "Yuzutaru"))
        profileDB.profileDAO().insert(Profile(0, "Yustar"))
        val profileData = profileDB.profileDAO().getProfile("Yustar")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginListInputTest() = testScope.runTest {
        profileDB.profileDAO().insert(listOf(Profile(0, "Yuzutaru"), Profile(1, "Yustar")))
        val profileData = profileDB.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun insertProfileDataTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "yuzutaru"))
        val profileDataList = profileDB.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 1)
    }

    @Test
    fun insertProfileDataOneByOneTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "yuzutaru"))
        profileDB.profileDAO().insert(Profile(1, "yustar"))
        val profileDataList = profileDB.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 2)
    }

    @Test
    fun insertProfileDataOnConflictTest() = testScope.runTest {
        profileDB.profileDAO().insert(Profile(0, "yuzutaru"))
        profileDB.profileDAO().insert(Profile(0, "yustar"))
        val profileDataList = profileDB.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 1)
    }

    @Test
    fun insertProfileDataListInputTest() = testScope.runTest {
        profileDB.profileDAO().insert(listOf(Profile(0, "yuzutaru"), Profile(1, "yustar")))
        val profileDataList = profileDB.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 2)
    }
}