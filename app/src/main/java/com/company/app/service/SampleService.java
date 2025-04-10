package com.company.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.app.entity.Sample;
import com.company.app.repository.SampleRepository;

@Service
public class SampleService {

	private final SampleRepository sampleRepository;   //생성자 주입

	public SampleService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}
	
	//서비스 구현 => 서비스 로직 구성
	//목록 조회(getAllSamples())
	//select * from sample; => .findAll()
	public List<Sample> getAllSamples() {
		return sampleRepository.findAll();	//List<Sample) sampleList = new ArrayList<Sample>(); 
	}
	
	//샘플 1건 조회(no로 조회) => getSampleById(Long no)
	//select * from sample where no=1; => .findById(no); => SampleController
	public Optional<Sample> getSampleById(Long no) {
		return sampleRepository.findById(no);	//Sample sample = new Sample();
	}
	
	//샘플 저장(sample 객체로 전달 받아 저장) => saveSample(Sample sample)
	//insert into sample values (default, "김기태"); => .save(sample)
	public Sample saveSample(Sample sample) {
		return sampleRepository.save(sample);   //Sample sample = new Sample();
	}
	
	//샘플 삭제(no로 삭제) => deleteSample(Long no)
	//delete from sample where no=1; => .deleteById(no);
	public void deleteSample(Long no) {
		sampleRepository.deleteById(no);
	}

	//RestApiController의 샘플 상세보기
	public Sample getSampleByNo(Long no) {	//RestApiController
		return sampleRepository.findById(no).orElse(null);
	}
	
	//샘플 수정(sample)
	//update sample set name="" where no=1;   => .save(sample)
	public Sample updateSample(Sample sample) {
		return sampleRepository.save(sample);
	}
	
}