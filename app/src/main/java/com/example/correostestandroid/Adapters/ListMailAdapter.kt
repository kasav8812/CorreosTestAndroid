package com.example.correostestandroid.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.correostestandroid.R
import com.example.correostestandroid.databinding.ItemMailBinding

interface CallBackSelected {
    fun refreshData()
    fun deleteMail(mIdEmail: Int)
}

class ListMailAdapter (private val mMailsList: ArrayList<MailEntity>) : RecyclerView.Adapter<ListMailAdapter.DataViewHolder>() {
    lateinit var mCallback: CallBackSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder{
        val itemBinding = ItemMailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(mMailsList[position], mMailsList, position, mCallback)

    override fun getItemCount(): Int = mMailsList.size

    fun updateList(mList : List<MailEntity>, mCallback: CallBackSelected) {
        this.mCallback = mCallback
        mMailsList.clear()
        mMailsList.addAll(mList)
        this.notifyDataSetChanged()
    }

    class DataViewHolder (private val binding: ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var mSectionsArray : ArrayList<MailEntity>
        fun bind(mMail: MailEntity, mList: ArrayList<MailEntity>, pos: Int, mCallback: CallBackSelected) {
            mSectionsArray = mList
            binding.mEmisor.text = mMail.asunto
            binding.mSubject.text = mMail.emisor
            binding.mMessage.text = mMail.mensaje
            binding.mHour.text = mMail.hora
            setBold(binding)
            setColorImageDefault(binding)

            setTint(mMail.destacado, binding.mDestacado,Color.YELLOW)
            setTint(mMail.spam, binding.mSpam,Color.RED)
            setRead(mMail.leido)

            itemView.setOnClickListener{
                selectMail(pos)
                mCallback.refreshData()
            }

            binding.mDestacado.setOnClickListener{
                selectFav(pos)
                mCallback.refreshData()
            }

            binding.mSpam.setOnClickListener{
                selectSpam(pos)
                mCallback.refreshData()
            }

            binding.mDelete.setOnClickListener {
                selectDelete(pos)
                mCallback.deleteMail(mSectionsArray[pos].Id!!)
            }
        }

        private fun setTint(isEnable: Boolean, mImage: ImageView, mColor: Int){
            if(isEnable){
                mImage.setColorFilter(mColor, PorterDuff.Mode.SRC_IN)
            }else{
                mImage.setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)
            }
        }

        private fun setColorImageDefault(binding: ItemMailBinding){
            binding.mDestacado.setColorFilter(R.color.dark_grey)
            binding.mSpam.setColorFilter(R.color.dark_grey)
        }

        private fun setBold(binding: ItemMailBinding){
            binding.mEmisor.setTypeface(null, Typeface.BOLD)
            binding.mSubject.setTypeface(null, Typeface.BOLD)
            binding.mHour.setTypeface(null, Typeface.BOLD)
        }

        private fun setRead(mIsRead: Boolean){
            if(mIsRead) {
                binding.mEmisor.setTypeface(null, Typeface.NORMAL)
                binding.mSubject.setTypeface(null, Typeface.NORMAL)
                binding.mHour.setTypeface(null, Typeface.NORMAL)
            }
        }

        private fun selectMail(mPosSelected: Int){
            for (mPos in 0 until mSectionsArray.size) {
                if(mPosSelected == mPos){
                    mSectionsArray[mPos].leido = mSectionsArray[mPos].leido != true
                }else{
                    mSectionsArray[mPos].leido = mSectionsArray[mPos].leido
                }
            }
        }

        private fun selectFav(mPosSelected: Int){
            for (mPos in 0 until mSectionsArray.size) {
                if(mPosSelected == mPos){
                    mSectionsArray[mPos].destacado = mSectionsArray[mPos].destacado != true
                }else{
                    mSectionsArray[mPos].destacado = mSectionsArray[mPos].destacado
                }
            }
        }

        private fun selectSpam(mPosSelected: Int){
            for (mPos in 0 until mSectionsArray.size) {
                if(mPosSelected == mPos){
                    mSectionsArray[mPos].spam = mSectionsArray[mPos].spam != true
                }else{
                    mSectionsArray[mPos].spam = mSectionsArray[mPos].spam
                }
            }
        }

        private fun selectDelete(mPosSelected: Int){
            for (mPos in 0..mSectionsArray.size) {
                if(mPosSelected == mPos){
                    mSectionsArray[mPos].eliminado = true
                }
            }
        }

    }

}