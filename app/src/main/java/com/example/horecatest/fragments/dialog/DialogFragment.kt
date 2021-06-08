package com.example.horecatest.fragments.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horecatest.R
import com.example.horecatest.models.Dialog
import com.example.horecatest.models.Message
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_scroll.*


class DialogFragment : Fragment() {

    private val messages = ArrayList<Message>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?):
            View = inflater.inflate(R.layout.fragment_scroll, container, false)

    private lateinit var adapter: DialogRecyclerAdapter
//    private lateinit var adapter: QAdapter

    private lateinit var layoutManager: LinearLayoutManager

    private val page = 20
    private var paginator = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reloadMessages()

        layoutManager = LinearLayoutManager(context)

//        adapter = QAdapter(messages)
//        adapter = DialogRecyclerAdapter(ArrayList())
        adapter = DialogRecyclerAdapter(getPage(paginator))
//        adapter = DialogRecyclerAdapter(messages)

        recyclerDialog.layoutManager = layoutManager
        recyclerDialog.adapter = adapter
        recyclerDialog.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            private var isLoading = false
            override fun isLastPage() = false//paginator >= messages.count()/page - 1
            override fun isLoading() = isLoading
            override fun loadMoreItems() {
                Log.e("Grino", "loadMoreItems")
                isLoading = true
                paginator += 1
                val messagePage = getPage(paginator)
                recyclerDialog.post(Runnable { adapter.addData(messagePage) })
                isLoading = false
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("Grino", "onScrolled dy/100 = " + dy % 100)
                Log.e("Grino", "onScrolled dy = $dy")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //isLoading = newState == 0
                Log.e("Grino", "newState=$newState")
            }
        })

    }

    fun getPage(paginator: Int) : ArrayList<Message> {
        Log.e("Grino", "getPage = $paginator")
        val pageList = ArrayList<Message>()
        for (i in paginator * page until (paginator + 1) * page)
            pageList.add(messages[i])
        return pageList
    }

    private fun reloadMessages() {
        messages.clear()
        readJson()?.messages?.let { messages.addAll(it) }
    }

    // Считывает Json из файл из ассетов
    // Возвращает модель Dialog
    private fun readJson(): Dialog? =
        try {
            Gson().fromJson(
                activity!!.assets.open("strings.json")
                    .bufferedReader()
                    .use { it.readText() },
                Dialog::class.java
            )
        } catch (e: Exception) {
            // Ошибка считавыния из файла
            e.printStackTrace()
            null
        }

}