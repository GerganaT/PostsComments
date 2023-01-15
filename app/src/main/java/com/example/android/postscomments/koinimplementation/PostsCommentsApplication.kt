package com.example.android.postscomments.koinimplementation

import android.app.Application
import com.example.android.postscomments.networking.PostsCommentsApiService
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import com.example.android.postscomments.repo.PostsCommentsRepository
import com.example.android.postscomments.ui.PostsCommentsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

/** Class, used for dependency injection*/

class PostsCommentsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val koinModule = module {


            viewModel {
                PostsCommentsViewModel(
                    get() as PostsCommentsRepository
                )
            }


            single {
                PostsCommentsRepository(
                    get() as PostsCommentsApiService
                )
            }

            single { PostsCommentsApiServiceEntryPoint.apiService }
        }
        startKoin {
            androidContext(this@PostsCommentsApplication)
            modules(listOf(koinModule))
        }
    }
}
