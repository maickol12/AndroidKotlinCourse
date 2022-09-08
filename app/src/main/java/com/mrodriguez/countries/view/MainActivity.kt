package com.mrodriguez.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mrodriguez.countries.R
import com.mrodriguez.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout;
    lateinit var rvCountries: RecyclerView;
    lateinit var loading_view: ProgressBar;
    lateinit var list_error: TextView;
    lateinit var viewModel: ListViewModel;
    private val countriesAdapter = CountryListAdapter(arrayListOf());
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java);
        viewModel.refresh();

        rvCountries = findViewById(R.id.countriesList)
        list_error = findViewById(R.id.list_error)
        loading_view = findViewById(R.id.loading_view)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        rvCountries.layoutManager = LinearLayoutManager(this)
        rvCountries.adapter = countriesAdapter;

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false;
            viewModel.refresh()
        }

        observeViewModel();
    }
    fun observeViewModel(){
        viewModel.countries.observe(this) { countries ->
            countries?.let {
                rvCountries.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        }

        viewModel.countryLoadError.observe(this) { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe( this) { isLoading ->
            isLoading.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE;
                    rvCountries.visibility = View.GONE;
                }
            }
        }
    }
}