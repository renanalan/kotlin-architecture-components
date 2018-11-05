package br.com.iteris.sampleandroidarchitecture

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = UserAdapter()
        rv_user.adapter = adapter
        rv_user.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.getUsers().observe(this, Observer<ArrayList<User>> { users ->
            users?.let { adapter.setUsers(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newUserActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name = it.getStringExtra(NewUserActivity.EXTRA_REPLY)
                viewModel.create(name)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onClickFab(view:View) {
        val intent = Intent(this@MainActivity, NewUserActivity::class.java)
        startActivityForResult(intent, newUserActivityRequestCode)
    }

    companion object {
        const val newUserActivityRequestCode = 1
    }
}
