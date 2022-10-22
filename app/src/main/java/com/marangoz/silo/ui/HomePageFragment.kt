package com.marangoz.silo.ui

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.marangoz.silo.R
import com.marangoz.silo.adapters.FieldAdapter
import com.marangoz.silo.data.DataBaseHelper
import com.marangoz.silo.data.fieldDao
import com.marangoz.silo.databinding.FragmentHomePageBinding
import com.marangoz.silo.models.Field


class HomePageFragment : Fragment() {

     lateinit var design: FragmentHomePageBinding
    lateinit var adapter: FieldAdapter
    lateinit var fieldList : ArrayList<Field>

    var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "HomePageFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentHomePageBinding.inflate(inflater,container,false)

        MobileAds.initialize(requireActivity()) {}
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireActivity(),"ca-app-pub-9888144807124822/7472592184", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(ContentValues.TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(ContentValues.TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e(ContentValues.TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(ContentValues.TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(ContentValues.TAG, "Ad showed fullscreen content.")
            }
        }
        if (mInterstitialAd != null) {
            activity?.let { mInterstitialAd?.show(it) }
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }

        val vt = DataBaseHelper(requireActivity())
        fieldList = ArrayList()

        design.rv.setHasFixedSize(true)
        design.rv.layoutManager = LinearLayoutManager(activity)

        fieldList = fieldDao().allFields(vt)
        design.itemText.text=fieldList.size.toString() + " Fields"
        adapter = FieldAdapter(activity,fieldList)
        design.rv.adapter = adapter




        design.fabButton.setOnClickListener(){
            if (mInterstitialAd != null) {
                activity?.let { mInterstitialAd?.show(it) }
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            val pass = HomePageFragmentDirections.goToRegister(true,0,"a","a","a",0,0f,0)
            Navigation.findNavController(design.root).navigate(pass)
        }


        return design.root
    }








}