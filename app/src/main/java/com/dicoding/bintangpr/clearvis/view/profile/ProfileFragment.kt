package com.dicoding.bintangpr.clearvis.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bintangpr.clearvis.data.preference.UserPreference
import com.dicoding.bintangpr.clearvis.databinding.FragmentProfileBinding
import com.dicoding.bintangpr.clearvis.view.factory.ViewModelFactory
import com.dicoding.bintangpr.clearvis.view.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding?.logoutBtn?.setOnClickListener {
            profileViewModel.logout()
            Intent(requireContext(), LoginActivity::class.java).also { intent ->
                startActivity(intent)
                requireActivity().finish()
            }
        }

    }
    private fun setupViewModel(){
        profileViewModel.getUser().observe(viewLifecycleOwner, { user ->
            binding?.nameTv?.text = user.name
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}