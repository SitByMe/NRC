package com.ta.netredcat.entity

import android.os.Parcel
import android.os.Parcelable

class UserInfo(
    /**
     * 用户昵称
     */
    internal var nickname: String,
    /**
     * 用户电话
     */
    internal var phone: String,
    /**
     * 简介
     */
    internal var introduction: String,
    /**
     * 用户头像
     */
    internal var face: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nickname)
        writeString(phone)
        writeString(introduction)
        writeString(face)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserInfo> = object : Parcelable.Creator<UserInfo> {
            override fun createFromParcel(source: Parcel): UserInfo = UserInfo(source)
            override fun newArray(size: Int): Array<UserInfo?> = arrayOfNulls(size)
        }
    }
}
