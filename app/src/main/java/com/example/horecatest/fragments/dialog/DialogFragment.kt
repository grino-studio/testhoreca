package com.example.horecatest.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horecatest.R
import com.example.horecatest.models.Dialog
import com.example.horecatest.models.Message
import com.example.horecatest.repository.JsonRepository
import com.example.horecatest.ui.listeners.PaginationScrollListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_scroll.*


class DialogFragment : Fragment() {

    private lateinit var adapter: DialogRecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var page = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?):
            View = inflater.inflate(R.layout.fragment_scroll, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        JsonRepository.readMessages(context)

        layoutManager = LinearLayoutManager(context)
        adapter = DialogRecyclerAdapter(JsonRepository.getPage(page))

        recyclerDialog.layoutManager = layoutManager
        recyclerDialog.adapter = adapter
        recyclerDialog.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            private var isLoading = false
            override fun isLastPage() = JsonRepository.isLastPage(page)
            override fun isLoading() = isLoading
            override fun loadMoreItems() {
                isLoading = true
                page += 1
                val messagePage = JsonRepository.getPage(page)
                recyclerDialog.post(Runnable { adapter.addData(messagePage) })
                isLoading = false
            }
        })

    }

}