package com.example.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.ProduitDao;
import com.example.entity.Produit;


@Controller
public class ProduitController {
@Autowired
	private ProduitDao produirepository;
	

@RequestMapping(value={"", "/", "/index"},method=RequestMethod.GET)
public String index(Model mod,@Valid @ModelAttribute(value="produit") Produit p,BindingResult bindingResult)
{
	List<Produit> produis=produirepository.findAll();
	mod.addAttribute("lesproduits",produis);
	return "produit";
	
}



@RequestMapping(value="/suup",method=RequestMethod.GET)
public String supprime(Model mod, Long idp ,@Valid @ModelAttribute(value="produit") Produit p,BindingResult bindingResult)
{
	produirepository.delete(idp);
	List<Produit> produis=produirepository.findAll();
	mod.addAttribute("lesproduits",produis);
	
	return "produit";
	
}


@RequestMapping(value="/add",method=RequestMethod.POST)
public String add(Model mod,@RequestParam(value="file")MultipartFile fileup,@Valid @ModelAttribute(value="produit") Produit p,BindingResult bindingResult ,HttpServletRequest request) throws IOException
{ 
	if(!produirepository.findBydesignation(p.getDesignation()).isEmpty())
	{
		Long jk=produirepository.findBydesignation(p.getDesignation()).get(0).getId();				
		produirepository.updateProduit(p.getDesignation(), p.getPrix(),jk);
		
		produirepository.findallafteredit().forEach(i->System.out.println(i.getPrix()));
	}
	else
	{		
	if(!bindingResult.hasErrors())
	{
	if(fileup!=null)
	{
		BufferedImage img=ImageIO.read(fileup.getInputStream());
		p.setPhoto(fileup.getBytes());
	}
	produirepository.save(p);
	}
	}
	
	List<Produit> produis=produirepository.findAll();
	mod.addAttribute("lesproduits",produis);		
	return "produit";	
}
@RequestMapping(value="/photoprod",produces=MediaType.IMAGE_JPEG_VALUE)
@ResponseBody
public byte[] photoprod(Model mod,@ModelAttribute(value="produit") Produit p,Long idphoto) throws IOException
{ 
	byte [] returne=IOUtils.toByteArray(new ByteArrayInputStream(produirepository.getOne(idphoto).getPhoto()));
	
	return returne;
}

@RequestMapping(value="/edit",method=RequestMethod.GET)
public String edit(Model mod, Long idp ,@Valid @ModelAttribute(value="produit") Produit p,BindingResult bindingResult)
{
	Produit ppp=produirepository.findOne(idp);
	mod.addAttribute("produit",ppp);
	List<Produit> produis=produirepository.findAll();
	mod.addAttribute("lesproduits",produis);
	return "produit";
	
}

}
