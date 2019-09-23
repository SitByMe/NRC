package com.ta.netredcat.ui.mvp

import com.ta.netredcat.data.db.DBHelperImpl
import com.ta.netredcat.data.net.ApiHelperImpl
import com.ta.netredcat.data.sp.SpHelperImpl

interface DataManagerImpl : DBHelperImpl, ApiHelperImpl, SpHelperImpl
