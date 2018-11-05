package br.com.iteris.sampleandroidarchitecture

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
    }

    fun onClickSave(view: View) {
        val replyIntent = Intent()
        if (TextUtils.isEmpty(et_name.text)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val user = et_name.text.toString()
            replyIntent.putExtra(EXTRA_REPLY, user)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "br.com.iteris.sampleandroidarchitecture.NewUserActivity.REPLY"
    }
}