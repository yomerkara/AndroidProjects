package com.arthas.lefrango.ui.menu.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.FragmentHomeBinding
import com.arthas.lefrango.ui.base.BaseFragment
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.util.extensions.RestaurantsAddList
import org.koin.android.ext.android.get

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    //private lateinit var adapter: ChatBotMessageAdapter
    //private val botList = listOf("Arthas", "Demo", "Luigi", "Igor")
    override val viewModel: HomeViewModel = get()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun initUI(view: View) {
        viewModel.name.value = viewModel.sharedPref.name
        binding?.tvTitle?.text = getString(R.string.welcome, viewModel.name.value)
        val viewPager2 = binding?.viewPagerRestaurantSlider

        binding?.viewPagerRestaurantSlider?.apply {
            adapter = SliderAdapter(RestaurantsAddList().listOfRestaurants())
            offscreenPageLimit = 7
            setPageTransformer(SliderPageTransformer(3))
        }


        viewPager2?.clipToPadding = false
        viewPager2?.clipChildren = false
        viewPager2?.offscreenPageLimit = 3
        viewPager2?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        //viewPager2?.registerOnPageChangeCallback()

        /*recyclerView()
        val random = (0..3).random()
        customMessage("Hello!,Today you're speaking with ${botList[random]},how my I help?")*/
    }

    /*override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                binding?.rvMessageItem?.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }*/

    override fun initListener() {

        /*binding?.btnSend?.setOnClickListener {
            sendMessage()
        }
        binding?.etMessage?.setOnClickListener {
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding?.rvMessageItem?.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }*/
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is HomeVMState.OnExpand -> {

            }
        }
    }

/*    fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(
                    ChatBotMessage(
                        message,
                        Constants.SharedPref.receiveID,
                        timeStamp
                    )
                )

                binding?.rvMessageItem?.scrollToPosition(adapter.itemCount - 1)
            }
        }

    }

    private fun recyclerView() {
        adapter = ChatBotMessageAdapter()
        binding?.rvMessageItem?.adapter = adapter
        binding?.rvMessageItem?.layoutManager = LinearLayoutManager(context)
    }

    fun sendMessage() {
        val message = binding?.etMessage?.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            binding?.etMessage?.setText("")

            adapter.insertMessage(ChatBotMessage(message, Constants.SharedPref.sendID, timeStamp))
            binding?.rvMessageItem?.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)
                adapter.insertMessage(
                    ChatBotMessage(
                        response,
                        Constants.SharedPref.receiveID,
                        timeStamp
                    )
                )
                binding?.rvMessageItem?.scrollToPosition(adapter.itemCount - 1)

                when (response) {
                    Constants.SharedPref.openGoogle -> {
                        val webSite = Intent(Intent.ACTION_VIEW)
                        webSite.data = Uri.parse("https://www.google.com")
                        startActivity(webSite)
                    }

                    Constants.SharedPref.openSearch -> {
                        val webSite = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String = message.substringAfter("search")
                        webSite.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(webSite)
                    }
                }
            }
        }

    }*/

}
