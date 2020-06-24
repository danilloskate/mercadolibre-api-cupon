Ejercicio entrevista: Cupón
Mercado Libre está implementando un nuevo beneficio para los usuarios que más usan la
plataforma con un cupón de cierto monto gratis que les permitirá comprar tantos items
marcados como favoritos que no excedan el monto total. Para esto se está analizando
construir una API que dado una lista de item_id y el monto total pueda darle la lista de items
que maximice el total gastado sin excederlo.
Aclaraciones:
- Sólo se puede comprar una unidad por item_id.
- No hay preferencia en la cantidad total de items siempre y cuando gasten el máximo
posible.
- El precio puede contener hasta 2 decimales.

Se creo una version de el api solicitado en spring boot teniendo en cuenta los objetivos planteados por el enunciado
se publico el api en google app engine en el siguiente link https://mercadolibre-api-cupon.uc.r.appspot.com

Como probar la Cupon

Para poder probar la aplicación, la API tiene definida el verbo POST que se mapea con el mapping /cupon, por lo tanto para ejecutarla
se prueba mediante una aplicación que permita realizar llamadas a web services como puede ser SoapUI y/o Postman.

https://mercadolibre-api-cupon.uc.r.appspot.com/coupon (no segura)
POST -> https://apicoupon.rj.r.appspot.com/coupon

Body (JSON)
{
"item_ids": ["MLA858591493", "MLA764012028", "MLA3", "MLA4", "MLA5"],
"amount": 5000
}

La API recupera los precios de los items realizando una petición GET a la api de items de mercado libre: https://api.mercadolibre.com/items/$ITEM_ID1 donde $ITEM_ID1 es el id de cada uno de los items recibidos en el request.
