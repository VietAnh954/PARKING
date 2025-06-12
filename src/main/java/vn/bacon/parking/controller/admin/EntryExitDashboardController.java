package vn.bacon.parking.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.service.EntryExitService;
import vn.bacon.parking.service.StaffService;

@Controller
@RequestMapping("/admin/entry-exit")
public class EntryExitDashboardController {

    private static final Logger logger = LoggerFactory.getLogger(EntryExitDashboardController.class);

    private final EntryExitService entryExitService;
    private final StaffService staffService;

    public EntryExitDashboardController(EntryExitService entryExitService, StaffService staffService) {
        this.entryExitService = entryExitService;
        this.staffService = staffService;
    }

    @GetMapping
    public String listEntries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortByTime,
            @RequestParam(required = false, defaultValue = "false") boolean showNotExitedOnly,
            Model model) {
        logger.debug("Listing entries with page={}, size={}, sortByTime={}, showNotExitedOnly={}", page, size,
                sortByTime, showNotExitedOnly);
        Sort sort = Sort.by("tgVao").descending();
        if ("asc".equalsIgnoreCase(sortByTime)) {
            sort = Sort.by("tgVao").ascending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EntryExitDetail> entryPage;
        if (showNotExitedOnly) {
            entryPage = entryExitService.getEntriesNotExited(pageable);
        } else {
            entryPage = entryExitService.getAllEntries(pageable);
        }
        model.addAttribute("entryPage", entryPage);
        model.addAttribute("currentSort", sortByTime);
        model.addAttribute("showNotExitedOnly", showNotExitedOnly);
        return "admin/entry-exit/show";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.debug("Showing add entry form");
        model.addAttribute("entry", new EntryExitDetail());
        return "admin/entry-exit/add";
    }

    @PostMapping("/add")
    public String addEntry(@RequestParam String bienSoXe,
            RedirectAttributes redirectAttributes) {
        logger.debug("Processing add entry for bienSoXe={}", bienSoXe);
        try {
            String maNVVao = staffService.getCurrentStaff().getMaNV();
            EntryExitDetail entry = entryExitService.processVehicleEntry(bienSoXe, maNVVao);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Xe " + bienSoXe + " đã được ghi nhận vào bãi. Giá: "
                            + (entry.getGia() != null ? entry.getGia() : 0) + " VNĐ");
        } catch (Exception e) {
            logger.error("Error adding entry: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/entry-exit";
    }

    @PostMapping("/exit")
    public String processExit(@RequestParam Integer maCTVaoRa,
            RedirectAttributes redirectAttributes) {
        logger.debug("Processing exit for maCTVaoRa={}", maCTVaoRa);
        try {
            String maNVRa = staffService.getCurrentStaff().getMaNV();
            EntryExitDetail entry = entryExitService.processVehicleExit(maCTVaoRa, maNVRa);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Xe " + (entry.getBienSoXe() != null ? entry.getBienSoXe().getBienSoXe() : "N/A")
                            + " đã được ghi nhận ra. Giá: "
                            + (entry.getGia() != null ? entry.getGia() : 0) + " VNĐ");
        } catch (Exception e) {
            logger.error("Error processing exit: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/entry-exit";
    }
}