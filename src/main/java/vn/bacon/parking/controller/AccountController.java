package vn.bacon.parking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import vn.bacon.parking.domain.Account;
import vn.bacon.parking.service.AccountService;

@Controller

public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Show all accounts
    @GetMapping("/admin/account")
    public String getAccountPage(Model model) {
        List<Account> accounts = this.accountService.getAllAccounts();

        model.addAttribute("accountshow", accounts);
        return "admin/account/show";
    }

    // Create new account
    @GetMapping("/admin/account/create")
    public String getCreateAccountPage(Model model) {
        model.addAttribute("newAccount", new Account());
        return "admin/account/create";
    }

    @PostMapping("/admin/account/create")
    public String createAccount(@ModelAttribute("newAccount") Account account1) {
        this.accountService.saveAccount(account1);
        return "redirect:/admin/account";
    }

    // Update account
    @RequestMapping("/admin/account/update/{maTK}")
    public String getUpdateAccountPage(Model model, @PathVariable String maTK) {
        Optional<Account> currentAccount = this.accountService.getAccountById(maTK);
        model.addAttribute("newAccount", currentAccount.get());
        return "admin/account/update";
    }

    @PostMapping("/admin/account/update")
    public String updateAccount(Model model, @ModelAttribute("newAccount") Account account1) {
        Account currentAccount = this.accountService.getAccountById(account1.getMaTK()).get();
        if (currentAccount != null) {
            currentAccount.setMaTK(account1.getMaTK());
            currentAccount.setPassword(account1.getPassword());
            currentAccount.setLoaiTK(account1.getLoaiTK());
            currentAccount.setMaNV(account1.getMaNV());
            currentAccount.setMaSV(account1.getMaSV());
            this.accountService.saveAccount(currentAccount);
        }

        return "redirect:/admin/account";
    }

    // Delete account
    @GetMapping("/admin/account/delete/{maTK}")
    public String getDeleteAccountPage(Model model, @PathVariable String maTK) {
        model.addAttribute("maTK", maTK);

        model.addAttribute("newAccount", new Account());
        return "admin/account/delete";
    }

    @PostMapping("/admin/account/delete")
    public String deleteAccount(@ModelAttribute("newAccount") Account account1) {
        this.accountService.deleteAccountById(account1.getMaTK());
        return "redirect:/admin/account";
    }

    // Xử lý lọc theo loại tài khoản
    @GetMapping("/admin/account/filter")
    public String filterAccount(@RequestParam String loaiTK, Model model) {

        model.addAttribute("accountshow", accountService.getAccountsByLoaiTK(loaiTK));
        return "admin/account/show";
    }

}
