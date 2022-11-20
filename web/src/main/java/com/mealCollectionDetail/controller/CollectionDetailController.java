package com.mealCollectionDetail.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mealCollectionDetail.model.CollectionDetailService;
import com.mealCollectionDetail.model.CollectionDetailVO;

@Controller
@RequestMapping("/mealCollect")
public class CollectionDetailController {

//    @Autowired
    private CollectionDetailService collectionDetailSV;

//    @Autowired
    private HttpServletRequest req;

    public CollectionDetailController(CollectionDetailService collectionDetailSV, HttpServletRequest req) {
        this.collectionDetailSV = collectionDetailSV;
        this.req = req;
    }

    @RequestMapping("/insert")
    public String insert(Model model, @ModelAttribute("collectionDetail") CollectionDetailVO collectionDetail) {


        if (validExist(collectionDetail)) {
            collectionDetailSV.insert(collectionDetail);
            model.addAttribute("collectionResult", "已將商品加入收藏清單");
            return returnPath(collectionDetail);

        } else {
            model.addAttribute("collectionResult", "此商品已在您的收藏清單中");
            return returnPath(collectionDetail);
        }

    }

    @RequestMapping("/list")
    public String ListCollection(Model model) {
        Integer memberNo=1;
        List<CollectionDetailVO> collectionDetails = collectionDetailSV.listAll(memberNo);
        model.addAttribute("collectionDetails", collectionDetails);
        return "mealCollect/ListCollection.jsp";

    }

    @RequestMapping("/delete")
    public String deleteByMemberNoAndMealNo(@Param("membeNo") Integer detailNo) {
        collectionDetailSV.deletById(detailNo);
        return "mealCollect/list";
    }

    private boolean validExist(CollectionDetailVO collectionDetail) {
        CollectionDetailVO collectionDetail2 = collectionDetailSV.findByMemberNoAndMealNo(collectionDetail.getMemberNo(), collectionDetail.getMealNo());
        if (collectionDetail2!=null&&collectionDetail.getMemberNo().equals(collectionDetail2.getMemberNo()) && collectionDetail.getMealNo().equals(collectionDetail2.getMealNo())) {
            return false;
        }
        return true;
    }

    public String returnPath(CollectionDetailVO collectionDetail) {
        String referer = req.getHeader("referer");
        String website =req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()+"/web/meal/mealController?action=findByprod&mealNo="+collectionDetail.getMealNo();
        if(referer.equals(website)){
            return "meal/mealController?action=findByprod&mealNo="+collectionDetail.getMealNo();

        }
        return "meal/mealController?action=listAll";
    }
}
