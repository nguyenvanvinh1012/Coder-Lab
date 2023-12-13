package com.example.coderlab.controller.client;

import com.example.coderlab.entity.Assignment;
import com.example.coderlab.repository.AssignmentRepository;
import com.example.coderlab.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problemSolving")
public class ProblemSolvingRestController {
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @GetMapping("")
    public Page<Assignment> list(Pageable pageable){
        return assignmentRepository.findAll(pageable);
    }
    @GetMapping("/search")
    public Page<Assignment> searchAssignment(@RequestParam("searchText") String searchText, Pageable pageable){
        return assignmentService.searchAssignmentByName(searchText, pageable);
    }
    @GetMapping("/searchTopic")
    public Page<Assignment> searchAssignmentTopic(@RequestParam("searchText") String searchText, Long languageId, Pageable pageable){
        return assignmentService.searchAssignmentTopicByName(searchText,languageId, pageable);
    }
    @GetMapping("/topic")
    public Page<Assignment> listAssignmentByTopic(@RequestParam("languageId") Long id, Pageable pageable){
        return assignmentService.listAssignmentByTopic(id, pageable);
    }
    @GetMapping("/filter")
    public Page<Assignment> filterAssignment(@RequestParam("easyChecked") Boolean easy,
                                             @RequestParam("mediumChecked") Boolean medium,
                                             @RequestParam("hardChecked") Boolean hard, Pageable pageable){
        if(!easy && !medium && !hard){
            return assignmentRepository.findAll(pageable);
        }
        return assignmentService.filterAssignment(easy,medium,hard,pageable);
    }
    @GetMapping("/filterOfTopic")
    public Page<Assignment> filterAssignmentTopic(@RequestParam("easyChecked") Boolean easy,
                                                @RequestParam("mediumChecked") Boolean medium,
                                                @RequestParam("hardChecked") Boolean hard, @RequestParam("languageId") Long languageId, Pageable pageable){
        if(!easy && !medium && !hard){
            return assignmentService.listAssignmentByTopic(languageId, pageable);
        }
        return assignmentService.filterAssignmentTopic(easy,medium,hard,languageId,pageable);
    }
}
