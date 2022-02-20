package com.example.cap
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import com.example.cap.databinding.ActivityMyStoreBinding
import com.example.cap.databinding.DialogBtnBinding
import kotlinx.android.synthetic.main.dialog_btn.*
import java.security.AccessController.getContext

class CustomDialog : DialogFragment() {
    private var _binding: DialogBtnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogBtnBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.foodsettingBtn.setOnClickListener {
            val setintent = Intent(this.context , Store2::class.java)
            startActivity(setintent)
            dismiss()    // 대화상자를 닫는 함수
        }
        binding.reviewBtn.setOnClickListener {
            val reviewintent = Intent(this.context , ReviewActivity::class.java)
            startActivity(reviewintent)
            dismiss()
        }
        binding.cancelBtn.setOnClickListener {
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