package com.ibnu.artalele.ui.home.tambah

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.databinding.TambahTransaksiFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_INCOME
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_SPENDING
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.SharedPreferencesManager
import timber.log.Timber
import java.util.*

class TambahTransaksiFragment : Fragment() {

    private var isEdit = false
    private var category = ""
    private var formattedWeight = ""
    private var grandTotal: Int = 0
    private var date = ""
    private var totalSpending = 0
    private var categoryId = 0
    private var description = ""
    private val calendar: Calendar = Calendar.getInstance()

    private var categoryName = ""
    private var categoryImage = 0


    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(TambahTransaksiViewModel::class.java)
    }

    private var _bindingTambahTraksaksiFragment: TambahTransaksiFragmentBinding? = null
    private val binding get() = _bindingTambahTraksaksiFragment

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            showDate(calendar)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _bindingTambahTraksaksiFragment =
            TambahTransaksiFragmentBinding.inflate(inflater, container, false)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onCancelTransaction()
                }

            })
        return _bindingTambahTraksaksiFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { TambahTransaksiFragmentArgs.fromBundle(it) }
        isEdit = safeArgs?.isEdit ?: false
        val fTotal = safeArgs?.passTotal
        val fWeight = safeArgs?.passWeight
        val fDescription = safeArgs?.passDescription
        val fDate = safeArgs?.passDate
        val fCategoryId = safeArgs?.passCategoryId

        if (isEdit) {
            if (fCategoryId == 0) {
                Timber.d("KOSONG")
            } else {
                initiateCategory(fCategoryId ?: 0, ArtaLeleHelper.addRupiahToAmount(fTotal ?: 0))
            }
            Timber.d("Test id = $fCategoryId, date = $fDate,description = $fTotal")
            binding?.tvDate?.text = fDate
            binding?.tvDesc?.text = fDescription
            initiateButtons()
        } else {
            initiateButtons()
            initiateViews()
            initiateDescriptionView()
        }
    }

    private fun initiateButtons() {
        binding?.selectTotalOrWeight?.setOnClickListener {
            if (category.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Pilih Category terlebih dahulu!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val action: NavDirections = if (category == INCOME) {
                    TambahTransaksiFragmentDirections.actionTambahTransaksiFragmentToCalculatorFragment(
                        CALCULATOR_INCOME
                    )
                } else {
                    TambahTransaksiFragmentDirections.actionTambahTransaksiFragmentToCalculatorFragment(
                        CALCULATOR_SPENDING
                    )
                }
                it.findNavController().navigate(action)
            }
        }

        binding?.selectCategory?.setOnClickListener {
            it.findNavController().navigate(R.id.action_tambahTransaksiFragment_to_categoryFragment)
        }

        binding?.selectDate?.setOnClickListener {
            ArtaLeleHelper.getDatePickerDialog(requireContext(), dateSetListener).show()
        }

        binding?.addNote?.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tambahTransaksiFragment_to_deskriptionFragment)
        }

        binding?.toolbar?.imgBack?.setOnClickListener {
            onCancelTransaction()
        }

        binding?.toolbar?.btnSave?.setOnClickListener {
            val day = ArtaLeleHelper.getDayFromDate(date)
            val month = ArtaLeleHelper.getMonthFromDate(date)
            val year = ArtaLeleHelper.getYearFromDate(date)

            val transactionCategory = CategoryEntity(
                categoryId = categoryId,
                categoryGroup = category,
                categoryImage = categoryImage,
                categoryName = categoryName
            )

            val spending = SpendingEntity(
                day = day,
                month = month,
                year = year,
                total = totalSpending,
                category = transactionCategory,
                description = description
            )
            val income = IncomeEntity(
                weight = formattedWeight,
                total = grandTotal,
                description = description,
                day = day,
                month = month,
                year = year,
                category = transactionCategory
            )

            try {
                if (category == INCOME) {
                    viewModel?.insertIncomeTransaction(income)
                } else {
                    viewModel?.insertSpendingTransaction(spending)
                }
            } finally {
                view?.findNavController()?.popBackStack()
            }
        }
    }

    private fun initiateViews() {
        categoryId = SharedPreferencesManager(requireContext()).getTransCategory
        if (categoryId == 0) {
            binding?.imgCategory?.setImageResource(R.drawable.ic_help)
            binding?.tvCategory?.text =
                requireContext().resources.getString(R.string.tambah_kategori_baru)
        } else {
            initiateCategory(categoryId, null)
        }
        this.date = SharedPreferencesManager(requireContext()).getTransDate ?: ""
        binding?.tvDate?.text = date
    }

    private fun initiateCategory(idCategory: Int, mAmount: String?) {
        viewModel?.getCategory(idCategory)?.observe(viewLifecycleOwner, Observer {
            binding?.tvCategory?.text = it.categoryName
            binding?.imgCategory?.setImageResource(it.categoryImage)
            category = it.categoryGroup
            categoryName = it.categoryName
            categoryImage = it.categoryImage
            if (it.categoryGroup == INCOME) {
                isShowIncomeView(true)
            } else {
                isShowIncomeView(false)
            }
            setCalculatorResult(mAmount)
        })
    }

    private fun initiateDescriptionView() {
        viewModel?.getDescription(requireContext())?.observe(viewLifecycleOwner, Observer {
            binding?.tvDesc?.text = it
            this.description = it
            Timber.d("checking description $it")
        })
    }

    private fun isShowIncomeView(isIncome: Boolean) {
        if (isIncome) {
            binding?.cvGrandTotal?.visibility = View.VISIBLE
            binding?.tvTotalOrWeight?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.darkGrey
                )
            )
        } else {
            binding?.tvTotalOrWeight?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_red
                )
            )
            binding?.cvGrandTotal?.visibility = View.GONE
        }
    }

    private fun setCalculatorResult(amount: String?) {
        val weight = SharedPreferencesManager(requireContext()).getTransWeight
        totalSpending = SharedPreferencesManager(requireContext()).getTransResult
        if (category == INCOME) {
            binding?.tvTotalOrWeight?.text = amount ?: "0"
            if (weight != 0f) {
                formattedWeight = ArtaLeleHelper.addKgToWeight(weight)
                binding?.tvTotalOrWeight?.text = formattedWeight
                binding?.tvGrandTotal?.text = calculateGrandTotal(weight)
            }
        } else {
            binding?.tvTotalOrWeight?.text = amount ?: "0"
            val formattedAmount = ArtaLeleHelper.addRupiahToAmount(totalSpending)
            if (totalSpending != 0) {
                binding?.tvTotalOrWeight?.text = formattedAmount
            }
        }
    }

    private fun onCancelTransaction() {
        val dialogBuilder = android.app.AlertDialog.Builder(requireContext()).apply {
            setTitle("Batalkan Transaksi")
            setMessage("Apakah Anda yakin untuk membatalkan?")
            setNegativeButton("TIDAK") { p0, _ -> p0.dismiss() }
            setPositiveButton("IYA") { p0, _ ->
                SharedPreferencesManager(requireContext()).resetTransactionSegment()
                view?.findNavController()?.popBackStack()
                p0.dismiss()
            }
        }
        dialogBuilder.create().show()
    }

    private fun calculateGrandTotal(weight: Float): String {
        val basePrice = SharedPreferencesManager(requireContext()).getPrice
        grandTotal = ((basePrice * weight).toInt())

        Timber.d("check grand total $grandTotal")

        return ArtaLeleHelper.addRupiahToAmount(grandTotal)
    }

    private fun showDate(calendar: Calendar) {
        val date = ArtaLeleHelper.formatSelectedDate(calendar)
        SharedPreferencesManager(requireContext()).setTransactionDate(date)

        this.date = SharedPreferencesManager(requireContext()).getTransDate ?: "0"

        binding?.tvDate?.text = this.date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingTambahTraksaksiFragment = null
    }

}