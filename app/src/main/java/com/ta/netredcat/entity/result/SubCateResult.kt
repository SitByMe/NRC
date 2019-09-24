package com.ta.netredcat.entity.result

data class SubCateResult(
//    val accountData: AccountData,
    val category_parent: Int,
    val category_show: Int,
    val category_type: String,
    val data_list: List<Data>,
    val input_keywords: List<String>,
    val input_keywords_new: List<InputKeywordsNew>,
    val is_note: Int,
    val must_see_content: String
) {

    data class AccountData(
        val account_id: String,
        val category_id: Int,
        val category_name: String,
        val id: Int,
        val is_del: Int,
        val name: String,
        val status: Int,
        val url_address: String,
        val user_id: Int,
        val words_id: String
    )

    data class Data(
        val category_id: Int,
        val id: Int,
        val name: String,
        val price: String,
        val price_img: String,
        val times: Int
    )

    data class InputKeywordsNew(
        val btn_status: String,
        val name: String
    )
}