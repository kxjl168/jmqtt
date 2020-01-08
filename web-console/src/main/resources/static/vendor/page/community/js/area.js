/**
 * 
 */

function setArea(formele,response)
{
	
	$(formele).find("#province").val(response.province);
	$(formele).find("#province").trigger("change");
	$(formele).find("#city").val(response.city);
	$(formele).find("#city").trigger("change");
	$(formele).find("#district").val(response.district);
	$(formele).find("#district").val(response.district);
}