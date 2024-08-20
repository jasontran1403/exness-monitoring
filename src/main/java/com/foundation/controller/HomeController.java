package com.foundation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foundation.dto.ExnessRequest;
import com.foundation.dto.RealtimeConvertedDto;
import com.foundation.dto.RealtimeDataDto;
import com.foundation.dto.RealtimeDataProjection;
import com.foundation.dto.ResultsDto;
import com.foundation.entity.Exness;
import com.foundation.service.ExnessService;
import com.foundation.service.Mq4DataService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	Mq4DataService mq4Service;
	
	@Autowired
	ExnessService exService;

	private final String[] stringsArray = { "111072483", "52151570", "52151575", "52155138", "52158535", "117040026",
			"87115226", "111085934", "111089874", "111092635", "117058034", "87124057", "111093843", "141004267",
			"141006189", "87124059", "141011443", "141013071", "141016642", "141015284", "141021803", "141021811",
			"141021822", "141022795", "141015992", "141030765", "141033762", "141033763", "117062045", "141035796",
			"141037013", "141021825", "141037141", "141037140", "87128440", "141041660", "141044855", "141026265",
			"141045547", "141044761", "141044764", "141049005", "141050073", "141049651", "141051778", "117063185",
			"141042246", "141055895", "141057293", "141054825", "141059226", "141061023", "141062697", "141054279",
			"49151850", "49151793", "111098868", "49151807", "111100848", "111103630", "24164596", "24164647",
			"87138202", "87140964", "111109891", "24169117" };

	private final String[] stringsArrayHHL = { "87109459", "141000697", "141009238", "87126519", "52168875",
			"141014713", "141013791", "117061068", "141040056", "141040747", "141044490", "111097165", "141044474",
			"141050517", "111097515", "141050585", "141057944", "141059500", "117063958", "111098474", "87130059",
			"87131261", "111104275", "111110133", "87142036", "117066826", "87145391" };

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		Object loggedInUser = session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/login";
		}

		String loggedInUserLowerCase = loggedInUser.toString().toLowerCase();

		if (loggedInUserLowerCase.equals("long_phan@ymail.com")) {
			List<RealtimeConvertedDto> listResult = new ArrayList<>();
			List<RealtimeDataProjection> results = mq4Service.getRealtimeData();
			for (RealtimeDataProjection item : results) {
				RealtimeConvertedDto itemData = new RealtimeConvertedDto();
				itemData.setExnessId(item.getExnessId());
				itemData.setBalance(item.getBalance());
				Optional<Exness> exnessResult = exService.findByExness(item.getExnessId());
				if (exnessResult.isEmpty()) {
					continue;
				}
				Exness exness = exnessResult.get();
				if (exness.getServer().startsWith("E")) {
					itemData.setCurrency("Cent");
				} else {
					itemData.setCurrency("USD");
				}
				itemData.setLot(item.getLot());
				itemData.setEquity(item.getEquity());
				itemData.setMt4Server(exness.getServer());
				itemData.setPassword(exness.getPassword());
				itemData.setName(exness.getName());
				itemData.setServer(exness.getChatId());
				itemData.setUser(exness.getToken());
				itemData.setStatus(exness.getStatus());

				RealtimeDataDto listData = mq4Service.getRealtimeDataByExnessId(item.getExnessId());

				itemData.setListData(listData.getRealtimeData());

				listResult.add(itemData);

			}
			model.addAttribute("result", listResult);
		} else if (loggedInUserLowerCase.equals("trantuongthuy@gmail.com")) {
			Map<String, Integer> stringMap = new HashMap<>();

			// Đổ dữ liệu vào Map
			for (String str : stringsArray) {
				stringMap.put(str, Integer.parseInt(str));
			}

			List<RealtimeConvertedDto> listResult = new ArrayList<>();
			List<RealtimeDataProjection> results = mq4Service.getRealtimeData();
			for (RealtimeDataProjection item : results) {
				if (stringMap.containsKey(item.getExnessId())) {
					RealtimeConvertedDto itemData = new RealtimeConvertedDto();
					itemData.setExnessId(item.getExnessId());
					itemData.setBalance(item.getBalance());
					itemData.setLot(item.getLot());
					itemData.setEquity(item.getEquity());
					
					Optional<Exness> exnessResult = exService.findByExness(item.getExnessId());
					if (exnessResult.isEmpty()) {
						continue;
					}
					
					Exness exness = exnessResult.get();
					itemData.setServer(exness.getChatId());
					itemData.setUser(exness.getToken());
					itemData.setMt4Server(exness.getServer());
					itemData.setPassword(exness.getPassword());
					itemData.setName(exness.getName());
					RealtimeDataDto listData = mq4Service.getRealtimeDataByExnessId(item.getExnessId());

					itemData.setListData(listData.getRealtimeData());

					listResult.add(itemData);
				}
			}
			model.addAttribute("result", listResult);
		} else if (loggedInUserLowerCase.equals("lucas9.ho@gmail.com")) {
			Map<String, Integer> stringMapHHL = new HashMap<>();

			// Đổ dữ liệu vào Map
			for (String str : stringsArrayHHL) {
				stringMapHHL.put(str, Integer.parseInt(str));
			}

			List<RealtimeConvertedDto> listResult = new ArrayList<>();
			List<RealtimeDataProjection> results = mq4Service.getRealtimeData();
			for (RealtimeDataProjection item : results) {
				if (stringMapHHL.containsKey(item.getExnessId())) {
					RealtimeConvertedDto itemData = new RealtimeConvertedDto();
					itemData.setExnessId(item.getExnessId());
					itemData.setBalance(item.getBalance());
					itemData.setLot(item.getLot());
					itemData.setEquity(item.getEquity());
					Optional<Exness> exnessResult = exService.findByExness(item.getExnessId());
					if (exnessResult.isEmpty()) {
						continue;
					}
					Exness exness = exnessResult.get();
					itemData.setServer(exness.getChatId());
					itemData.setUser(exness.getToken());
					itemData.setMt4Server(exness.getServer());
					itemData.setPassword(exness.getPassword());
					itemData.setName(exness.getName());
					RealtimeDataDto listData = mq4Service.getRealtimeDataByExnessId(item.getExnessId());

					itemData.setListData(listData.getRealtimeData());

					listResult.add(itemData);
				}
			}
			model.addAttribute("result", listResult);
		}

		return "index";
	}

	@GetMapping("/controller")
	public String controller(Model model) {
		List<ResultsDto> listExness = exService.getAllExness();
		model.addAttribute("listExness", listExness);
		return "controller";
	}

	@GetMapping({ "/login" })
	public String login() {
		return "login";
	}
	
	@PostMapping("/add-exness")
    public String addExness(@RequestBody @Valid ExnessRequest request, Model model) {
        // Thực hiện logic để thêm exness
        // Đây chỉ là ví dụ, bạn có thể thêm logic lưu vào cơ sở dữ liệu hoặc xử lý khác tại đây
        System.out.println("Received Exness Request: " + request);

        // Thêm thông báo vào model
        model.addAttribute("message", "Thêm thành công!");

        // Trả về trang kết quả
        return "result";  // "result" là tên của template HTML
    }

	@PostMapping("/login/exec")
	public String loginProcess(@RequestParam("email") String email, @RequestParam("pass") String password,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (email.equalsIgnoreCase("long_phan@ymail.com") || email.equalsIgnoreCase("trantuongthuy@gmail.com")
				|| email.equalsIgnoreCase("lucas9.ho@gmail.com")) {
			session.setAttribute("loggedInUser", email);
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// Xoá session
		session.invalidate();

		// Chuyển hướng về trang đăng nhập hoặc trang chính
		return "redirect:/login";
	}
}
