$(document).ready(function() {

	var imageSlider = $('.image-slider');
	if (imageSlider.length > 0) {
		imageSlider.slick({
			dots: true,
			infinite: true,
			speed: 500,
			slidesToShow: 1,
			slidesToScroll: 1,
			autoplay: true,
			autoplaySpeed: 2000,
		});
	}
});