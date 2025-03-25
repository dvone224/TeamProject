function previewImage(input, index) {
      var previewImg = document.getElementById('previewImg' + index);
      var file = input.files[0];

      if (file) {
          var reader = new FileReader();

          reader.onload = function(e) {
              previewImg.src = e.target.result;
          }

          reader.readAsDataURL(file);
      }
  }