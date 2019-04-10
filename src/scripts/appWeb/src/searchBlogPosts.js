document.getElementById("match").addEventListener("input", function () {
    let search = $('#match').val();
    if (search === null){
        return;
    }
    let imageURL="imageUrl";

    if(search.length > 2){
        $.ajax({
            dataType: 'json',
            type: "GET",
            url: 'blog/search',
            data: {"q": search}
        })
            .done(function (data) {
                $('#container').empty();
                $(data).each(function (index, element) {
                    if(element.category.name==="Sport"){
                        imageURL = "../images/noah-silliman-246027-unsplash.jpg"
                    }else if(element.category.name==="Cultura"){
                        imageURL = "../images/annie-spratt-749650-unsplash.jpg"
                    }else if(element.category.name==="Scienza"){
                        imageURL = "../images/elevate-755051-unsplash.jpg"
                    }
                    $('#container').append(
                        '<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" data-aos="fade-right" >' +
                        '<article class="card text-center" >' +
                        '<div>' +
                        '<img class="card-img-top" src=' + imageURL + ' alt="" width="100%"/>' +
                        '</div>' +
                        '<div class="card-block">' +
                        '<h4 class="card-title" >' + element.title + '</h4>' +
                        '<p class="card-text"  >' + element.text + '</p>' +
                        '<a class="btn btn-default" href="../blog/' + element.id + '">Read More</a>' +
                        '</div>' +
                        '</article>' +
                        '</div>');
                })
            });
    }
});






document.getElementById("submitSearch").addEventListener('click',  function(e){
    e.preventDefault();
    let search = $('#match').val();
    if (search === null){
        return;
    }
    let imageURL="imageUrl";
        $.ajax({
            dataType: 'json',
            type: "GET",
            url: 'blog/search',
            data: {"q": search}
        })
            .done(function (data) {

                $('#container').empty();
                $(data).each(function (index, element) {
                    if(element.category.name==="Sport"){
                        imageURL = "../images/noah-silliman-246027-unsplash.jpg"
                    }else if(element.category.name==="Cultura"){
                        imageURL = "../images/annie-spratt-749650-unsplash.jpg"
                    }else if(element.category.name==="Scienza"){
                        imageURL = "../images/elevate-755051-unsplash.jpg"
                    }
                    $('#container').append(
                        '<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" data-aos="fade-right" >' +
                        '<article class="card text-center" >' +
                        '<div>' +
                        '<img class="card-img-top" src=' + imageURL + ' alt="" width="100%"/>' +
                        '</div>' +
                        '<div class="card-block">' +
                        '<h4 class="card-title" >' + element.title +'</h4>' +
                        '<p class="card-text"  >' + element.text +'</p>' +
                        '<a class="btn btn-default" href="../blog/'+ element.id +'">Read More</a>' +
                        '</div>' +
                        '</article>' +
                        '</div>');
                })
            });
});







