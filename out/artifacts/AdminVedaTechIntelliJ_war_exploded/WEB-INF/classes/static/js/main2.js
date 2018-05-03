/**
 * Created by z00382545 on 10/20/16.
 */

$(document).ready(function() {
	   // Executed when select is changed
	    $("#idB").on('click',function() {
	        var x = this.selectedIndex;
	        console.log("VALOR DE X ", x);
	        if (x == "") {
	           $("#submit-button1").hide();
	        } else {
	           $("#submit-button1").show();
	        }
	    });
	    
	    $(function() {
	    	 $('.desc').hide();
	     });
	    
	    $("input:radio[name='gridRadios']").click(function() {   
	    	
	        $('.desc').hide();
	        $('#' + $("input:radio[name='gridRadios']:checked").val()).show();
	    });
	    
	    
	    // It must not be visible at first time
	    $("#submit-button1").css("display","none");
	    
	    //------------------------------------------
	    
	    $("#typeTransaction").on('change',function() {
	        var x = this.selectedIndex;

	        if (x == "Payments from Customers") {
	           $("#select-customer").hide();
	        } else {
	           $("#select-customer").show();
	        }
	    });
	    
	    // It must not be visible at first time
	    $("#select-customer").css("display","none");
	    
	    $(document).ready(function(){
	        $("#typeTransaction").change(function(){
	            $(this).find("option:selected").each(function(){
	                var optionValue = $(this).attr("value");
	                if(optionValue){
	                    $(".box").not("." + optionValue).hide();
	                    $("." + optionValue).show();
	                } else{
	                    $(".box").hide();
	                }
	            });
	        }).change();
	    });
	    
	});