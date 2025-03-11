package net.azarquiel.firebasechat.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import net.azarquiel.firebasechat.MainActivity
import net.azarquiel.firebasechat.model.Post

class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    companion object {
        const val TAG = "FirebaseChat"
    }
    val db = Firebase.firestore

    val mainActivity by lazy { mainActivity }

    val openDialog = mutableStateOf(false)

    val mensajes = mutableStateListOf<Post>()

    init {
        setListener()
    }

    fun addPost(post: Post) {
        val doc = mapOf(
            "user" to post.user,
            "msg" to post.msg
        )
        db.collection("posts")
            .add(doc)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
    }

    private fun setListener() {
        db.collection("posts").addSnapshotListener { snapshot, e ->
            snapshot?.let {
                documentToList(snapshot.documents)
            }

            e?.let {
                Log.d(TAG, "Listen failed.", e)
            }
        }
    }

    private fun documentToList(documentos: List<DocumentSnapshot>) {
        mensajes.clear()
        documentos.forEach { documento ->
            val user = documento["user"] as String
            val msg = documento["msg"] as String
            mensajes.add(Post(user, msg))
        }
    }
}