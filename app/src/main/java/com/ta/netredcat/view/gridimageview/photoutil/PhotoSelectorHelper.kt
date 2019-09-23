package com.ta.netredcat.view.gridimageview.photoutil

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore

import com.blankj.utilcode.util.ToastUtils
import com.ta.netredcat.permission.PermissionUtils
import com.ta.netredcat.utils.Utils

import java.util.ArrayList

class PhotoSelectorHelper(private val context: Context) {

    private val resolver: ContentResolver = context.contentResolver
    private val handler: Handler = Handler(Looper.getMainLooper())

    /**
     * 获取最近照片列表
     */
    val current: List<String>
        get() {
            val photos = ArrayList<String>()
            val cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.DATE_ADDED,
                    MediaStore.Images.ImageColumns.SIZE
                ),
                MediaStore.Images.ImageColumns.SIZE + " > " + 1024 * 10,
                null,
                MediaStore.Images.ImageColumns.DATE_ADDED + "  DESC"
            )
                ?: return photos

            while (cursor.moveToNext()) {
                photos.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)))
            }

            if (!cursor.isClosed) {
                cursor.close()
            }

            return photos
        }

    fun getRecentPhotoList(listener: OnLoadPhotoListener) {
        Thread {
            if (Utils.isPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                val photos = current
                handler.post { listener.onPhotoLoaded(photos) }
            } else {
                val permission = object : PermissionUtils.RequestPermissionsResultListener {
                    override fun success() {
                        val photos = current
                        handler.post { listener.onPhotoLoaded(photos) }
                    }

                    override fun fail() {
                        ToastUtils.showShort("没有权限")
                    }
                }
                PermissionUtils.requestPermission(
                    context as Activity,
                    permission,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }.start()
    }

    /**
     * 获取本地图库照片回调
     */
    interface OnLoadPhotoListener {
        fun onPhotoLoaded(photos: List<String>)
    }
}

