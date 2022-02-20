package com.example.cap

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cap.databinding.DialongStoreBtnBinding

class CustomDialogStore : DialogFragment() {
    private var _binding: DialongStoreBtnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialongStoreBtnBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.yseBtn.setOnClickListener {
            val setintent = Intent(this.context , StoreSettingActivity::class.java)
            startActivity(setintent)
            dismiss()    // 대화상자를 닫는 함수
        }
        binding.noBtn.setOnClickListener {
            dismiss()
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnButtonClickListener {
        fun onButton1Clicked()
        fun onButton2Clicked()
        fun onButton3Clicked()
    }
    // 클릭 이벤트 설정
    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }
    // 클릭 이벤트 실행
    private lateinit var buttonClickListener: OnButtonClickListener
}