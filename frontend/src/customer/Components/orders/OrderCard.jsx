import { Box, Grid, Typography } from "@mui/material";
import FiberManualRecordIcon from "@mui/icons-material/FiberManualRecord";
import AdjustIcon from "@mui/icons-material/Adjust";
import StarIcon from "@mui/icons-material/Star";
import React from "react";
import { useNavigate } from "react-router-dom";

const OrderCard = ({ item, order }) => {
  const navigate = useNavigate();

  const formatDate = (date) => {
    if (!date) return "Delivery date unavailable";
    const deliveryDate = new Date(date);
    const options = { month: 'short', day: 'numeric' };
    return deliveryDate.toLocaleDateString("en-US", options);
  };

  return (
    <Box className="p-5 shadow-lg hover:shadow-2xl border">
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item xs={6}>
          <div
            onClick={() => navigate(`/account/order/${order?.id}`)}
            className="flex cursor-pointer"
          >
            <img
              className="w-[5rem] h-[5rem] object-cover object-top"
              src={item?.product?.imageUrl}
              alt={item?.product?.title || "Product Image"}
            />
            <div className="ml-5">
              <Typography variant="body1" className="mb-2">
                {item?.product?.title}
              </Typography>
              <Typography variant="body2" className="opacity-50 text-xs font-semibold space-x-5">
                <span>Size: {item?.size}</span>
              </Typography>
            </div>
          </div>
        </Grid>

        <Grid item xs={2}>
          <Typography variant="body1">₹{item?.price || "N/A"}</Typography>
        </Grid>

        <Grid item xs={4}>
          <div className="space-y-2 font-semibold">
            {order?.orderStatus === "DELIVERED" ? (
              <>
                <FiberManualRecordIcon
                  sx={{ width: "15px", height: "15px" }}
                  className="text-green-600 p-0 mr-2 text-sm"
                />
                <span>Delivered On {formatDate(order?.deliveryDate)}</span>
              </>
            ) : (
              <>
                <AdjustIcon
                  sx={{ width: "15px", height: "15px" }}
                  className="text-green-600 p-0 mr-2 text-sm"
                />
                <span>
                  {order?.orderStatus === "PENDING"
                    ? "Order not confirmed"
                    : order?.deliveryDate
                    ? `Expected delivery by ${formatDate(order?.deliveryDate)}`
                    : "Delivery date to be updated"}
                </span>
              </>
            )}
          </div>

          <Typography variant="body2" className="text-xs">
            {order?.orderStatus === "PENDING"
              ? "Your Order is yet to be confirmed"
              : order?.orderStatus === "SHIPPED"
              ? "Your Order Has Been Shipped"
              : "Your Order has been Delivered"}
          </Typography>

          {order?.orderStatus === "DELIVERED" && (
            <div
              onClick={() => navigate(`/account/rate/${order?.id}`)}
              className="flex items-center text-blue-600 cursor-pointer"
            >
              <StarIcon sx={{ fontSize: "2rem" }} className="px-2 text-5xl" />
              <Typography variant="body2">Rate & Review Product</Typography>
            </div>
          )}
        </Grid>
      </Grid>
    </Box>
  );
};

export default OrderCard;
