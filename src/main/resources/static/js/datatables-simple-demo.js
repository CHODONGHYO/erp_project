window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);

        $(".productList .listBtn.bg-danger").on("click", (e) => {
            let parent$Tr = $(e.target).parents("tr");
            let modalImage = $(parent$Tr).find(".image").attr("src");
            let modalProductId = $(parent$Tr).find(".productId").val();
            let modalProductName = $(parent$Tr).find(".productName").text();
            let modalBrandName = $(parent$Tr).find(".brandName").text();
            let modalMCategory = $(parent$Tr).find(".mCategory").text();
            let modalSCategory = $(parent$Tr).find(".sCategory").text();
            let modalOriginalPrice = $(parent$Tr).find(".originalPrice").text();
            let modalSellPrice = $(parent$Tr).find(".sellPrice").text();

            $(".modalImg").attr("src", modalImage);
            $(".modalProductId").val(modalProductId);
            $(".modalBrand").text(modalBrandName);
            $(".modalMCate").text(modalMCategory);
            $(".modalSCate").text(modalSCategory);
            $(".modalName").text(modalProductName);
            $(".modalOriginal").text(modalOriginalPrice);
            $(".modalSale").text(modalSellPrice);


            $('.myModal').show();
        })
    }
});
