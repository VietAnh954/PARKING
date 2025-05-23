package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.Account;
import vn.bacon.parking.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Optional<Account> getAccountById(String maTK) {
        return this.accountRepository.findById(maTK);
    }

    public void deleteAccountById(String maTK) {
        this.accountRepository.deleteById(maTK);
    }

    public List<Account> getAccountsByLoaiTK(String loaiTK) {
        return this.accountRepository.findByLoaiTK(loaiTK);
    }

    public List<Account> searchAccountsByKeyword(String keyword) {
        return this.accountRepository.timKiemTheoTuKhoa(keyword);
    }

    public Page<Account> getAccountPage(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
